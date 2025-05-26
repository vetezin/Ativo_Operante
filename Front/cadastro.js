const form = document.getElementById('cadastroForm');

form.addEventListener('submit',(event) =>{
    event.preventDefault();

    const cpf = document.getElementById('cpf').value.trim();
    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value.trim();
    const nivel = 2;

    const usuario = { cpf, email, senha, nivel };

    fetch('http://localhost:8080/apis/usuario',{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(async response=>{

        if(!response.ok)
            throw new Error("Erro ao cadastrar no banco");
        else{
            alert('Cadastro realizado com sucesso');
            window.location.href = "login.html";
        }
    });

})