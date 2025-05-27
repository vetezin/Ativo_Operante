const form = document.getElementById('loginForm');
form.addEventListener('submit', async (event) => {
  event.preventDefault();

  const email = document.getElementById('email').value.trim();
  const senha = document.getElementById('senha').value;

  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, senha })
    });

    if (!response.ok) {
      const msg = await response.text();
      throw new Error(msg || "Falha no login");
    }

    const data = await response.json();
    console.log("🔐 Dados recebidos no login:", data);

    // ✅ AJUSTE AQUI CONFORME A ESTRUTURA REAL DA RESPOSTA
    localStorage.setItem("token", data.token);
   localStorage.setItem("userId", data.id);
    localStorage.setItem("role", data.role);

    // Redirecionar com base no nível de acesso
    if (data.role === "ADMIN" || data.role === "1") {
      window.location.href = "admin.html";
    } else {
      window.location.href = "usuario.html";
    }

  } catch (error) {
    console.error("Erro:", error.message);
    alert("Erro no login: " + error.message);
  }
});
