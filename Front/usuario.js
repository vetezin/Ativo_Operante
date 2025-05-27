(() => {
  // Verifica se o token e o userId estão salvos no localStorage
  const usuarioID = localStorage.getItem("userId");
  const token = localStorage.getItem("token");

  console.log("📦 ID do usuário:", usuarioID);

  // Se não estiver logado, redireciona para o login
  if (!usuarioID || !token) {
    alert("Usuário não está logado ou ID/token não encontrado.");
    window.location.href = "login.html";
    return;
  }

  // 1. Buscar denúncias do usuário
  fetch(`http://localhost:8080/apis/denuncia/usuario/${usuarioID}`)
    .then(res => {
      console.log("🔄 Resposta do servidor:", res);
      if (!res.ok) {
        return res.json().then(err => {
          throw new Error(err?.mensagem || "Erro ao buscar denúncias");
        });
      }
      return res.json();
    })
    .then(denuncias => {
      console.log("📥 Denúncias recebidas:", denuncias);
      const lista = document.getElementById("listaDenuncias");
      lista.innerHTML = "";

      if (!denuncias.length) {
        lista.innerHTML = "<p>Você ainda não enviou nenhuma denúncia.</p>";
        return;
      }

      denuncias.forEach(denuncia => {
        const div = document.createElement("div");
        div.classList.add("denuncia-item");
        div.innerHTML = `
          <p><strong>Título:</strong> ${denuncia.titulo}</p>
          <p><strong>Texto:</strong> ${denuncia.texto}</p>
          <p><strong>Urgência:</strong> ${denuncia.urgencia}</p>
          <p><strong>Data:</strong> ${denuncia.data}</p>
          ${denuncia.feedBack ? `<p><strong>Feedback:</strong> ${denuncia.feedBack.texto}</p>` : ""}
        `;
        lista.appendChild(div);
      });
    })
    .catch(erro => {
      console.error("💥 Erro no fetch de denúncias:", erro);
      alert("Erro ao carregar denúncias: " + erro.message);
    });

  // 2. Envio da denúncia
  document.getElementById("denunciaForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const denuncia = {
      titulo: document.getElementById("titulo").value,
      texto: document.getElementById("texto").value,
      urgencia: parseInt(document.getElementById("urgencia").value),
      data: new Date().toISOString().split("T")[0], // ex: "2025-05-27"
      tipo: { id: parseInt(document.getElementById("tipo").value) },
      orgao: { id: parseInt(document.getElementById("orgao").value) },
      usuario: { id: parseInt(usuarioID) }
    };

    console.log("📤 Enviando denúncia:", denuncia);

    fetch("http://localhost:8080/apis/denuncia", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(denuncia)
    })
      .then(res => {
        if (!res.ok) {
          return res.json().then(err => {
            throw new Error(err?.mensagem || "Erro ao enviar denúncia");
          });
        }
        return res.json();
      })
      .then(data => {
        alert("✅ Denúncia enviada com sucesso!");
        location.reload(); // Recarrega a página para mostrar nova denúncia
      })
      .catch(erro => {
        console.error("❌ Erro ao enviar denúncia:", erro);
        alert("Erro ao enviar denúncia: " + erro.message);
      });
  });

  // 3. Carregar opções de tipo
  function carregarTipos() {
    fetch("http://localhost:8080/apis/tipo")
      .then(res => res.json())
      .then(tipos => {
        const selectTipo = document.getElementById("tipo");
        tipos.forEach(tipo => {
          const option = document.createElement("option");
          option.value = tipo.id;
          option.textContent = tipo.nome;
          selectTipo.appendChild(option);
        });
      })
      .catch(err => console.error("Erro ao carregar tipos:", err));
  }

  // 4. Carregar opções de órgão
  function carregarOrgaos() {
    fetch("http://localhost:8080/apis/orgaos")
      .then(res => res.json())
      .then(orgaos => {
        const selectOrgao = document.getElementById("orgao");
        orgaos.forEach(orgao => {
          const option = document.createElement("option");
          option.value = orgao.id;
          option.textContent = orgao.nome;
          selectOrgao.appendChild(option);
        });
      })
      .catch(err => console.error("Erro ao carregar órgãos:", err));
  }

  // Carrega selects ao abrir a página
  carregarTipos();
  carregarOrgaos();
})();
