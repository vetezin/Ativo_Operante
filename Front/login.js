const form = document.getElementById('loginForm');
form.addEventListener('submit', (event) => {
    event.preventDefault();

    const email = document.getElementById('email').value.trim();
   const senha = document.getElementById('senha').value;


    fetch(`http://localhost:8080/apis/usuario?email=${encodeURIComponent(email)}`)
    .then(async response => {
        if (!response.ok) {
            
            const err = await response.json();
            throw new Error(err.mensagem || "Usuário não encontrado");
        }
        return response.json();
    })
    .then(usuario => {
        console.log('Senha vinda do backend:', `"${usuario.senha}"`);

        console.log("Usuário recebido:", usuario);

        if (usuario.senha == senha) {
            console.log("Acesso liberado");

            if(usuario.email == "admin@admin.com"){
                window.location.href = "admin.html";
            }
            // else
            //     window.location.href = "usuario.html"

            
        } else {
            throw new Error("Senha incorreta");
        }
    })
    .catch(erro => {
        console.error("Erro:", erro.message);
        alert(erro.message);  
    });
});
