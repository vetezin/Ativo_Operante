document.addEventListener('DOMContentLoaded', () => {
    carregarOrgaos();
    carregarTiposProblemas();
    carregarDenuncias();
});


let orgaosLista = []; // variável global para armazenar órgãos

function carregarOrgaos() {
    fetch('http://localhost:8080/apis/orgaos')
        .then(res => res.json())
        .then(orgaos => {
            orgaosLista = orgaos; // salva a lista globalmente
            const lista = document.getElementById('orgaoLista');
            lista.innerHTML = '';
            orgaos.forEach(orgao => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <span>${orgao.nome}</span>
                    <button onclick="editarOrgao(${orgao.id})">Editar</button>
                    <button onclick="excluirOrgao(${orgao.id})">Excluir</button>
                `;
                lista.appendChild(li);
            });
        });
}


function editarOrgao(id) {
    const orgao = orgaosLista.find(o => o.id === id);
    if (!orgao) {
        alert('Órgão não encontrado');
        return;
    }
    const novoNome = prompt('Digite o novo nome do órgão:', orgao.nome);
    if (novoNome === null || novoNome.trim() === '') 
        return; // cancelou ou vazio

    const orgaoAtualizado = { ...orgao, nome: novoNome.trim() };

    fetch('http://localhost:8080/apis/orgaos', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(orgaoAtualizado)
    })
    .then(res => {
        if (!res.ok) throw new Error('Erro na atualização');
        return res.json();
    })
    .then(() => {
        alert('Órgão atualizado!');
        carregarOrgaos(); // recarrega a lista atualizada
    })
    .catch(err => alert(err.message));
}

function excluirOrgao(id) {
    const confirmar = confirm("Deseja mesmo excluir?");

    if (confirmar) {
        fetch(`http://localhost:8080/apis/orgaos/${id}`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
        })
        .then(response => {
            if (!response.ok)
                throw new Error('Erro ao excluir orgao');
            else {
                alert('Orgao excluido');
                carregarOrgaos();
            }
        })
        .catch(error => alert(error.message));
    }
}



function carregarTiposProblemas() {
    fetch('http://localhost:8080/apis/tipo')
        .then(res => res.json())
        .then(tipo => {
            const lista = document.getElementById('problemaLista');
            lista.innerHTML = '';
            tipo.forEach(tipo => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <span>${tipo.nome}</span>
                    <button onclick="editarTipo(${tipo.id}, '${tipo.nome}')">Editar</button>
                    <button onclick="excluirTipo(${tipo.id})">Excluir</button>
                `;
                lista.appendChild(li);
            });
        });
}


function editarTipo(id, nome) { 
    const novoNome = prompt("Digite o novo nome:", nome);

    if (novoNome && novoNome.trim() !== "") {
        fetch('http://localhost:8080/apis/tipo', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: id, nome: novoNome.trim() })
        })
        .then(res => {
            if (res.ok) {
                alert("Tipo atualizado");
                carregarTiposProblemas();
            } else {
                alert("Erro ao atualizar tipo");
            }
        })
        .catch(() => alert("Erro na requisição"));
    }
}


function excluirTipo(id){
    const confirmar = confirm("Deseja mesmo excluir?");

    if(confirmar){

        fetch(`http://localhost:8080/apis/tipo/${id}`,{
            method:"DELETE",
            headers: { 'Content-Type': 'application/json' },
        })
        .then(res=>{
            if(!res.ok)
                throw new Error("Erro ao excluir");
            else{
                alert("Sucesso na exclusao");
                carregarTiposProblemas()
                carregarDenuncias();
            }
        }).catch(error => alert(error.message));
    }

}


function carregarDenuncias() {
    fetch('http://localhost:8080/apis/denuncia')
        .then(res => res.json())
        .then(denuncia => {
            const lista = document.getElementById('denunciasLista');
            lista.innerHTML = '';
            denuncia.forEach(denuncia => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <span>Titulo: ${denuncia.titulo}</span>
                    <button onclick='visualizarDen(${JSON.stringify(denuncia)})'>Visualizar</button>
                    <button onclick="ExcluirDen(${denuncia.id})">Excluir</button>
                    <button onclick="feedbackDen(${denuncia.id})">Feedback</button>
                `;
                lista.appendChild(li);
            });
        });
}

function visualizarDen(denuncia) {
    alert(
        "Texto: " + denuncia.texto + "\n" +
        "Urgência: " + denuncia.urgencia + "\n" +
        "Data: " + denuncia.data + "\n" +
        "Tipo: " + denuncia.tipo.nome + "\n" +
        "Órgão: " + denuncia.orgao.nome
    );
}

function feedbackDen(id){

    const feedback = prompt("Adicionar feedback:");


    if(feedback.trim() != ""){
        fetch(`http://localhost:8080/apis/denuncia/add-feedback/${id}/${encodeURIComponent(feedback)}`, {
            method: 'POST'
        })
        .then(res=>{
            if(!res.ok)
                throw new Error("Erro ao inserir feedback");
            else{
                 alert("Feedback adicionado com sucesso!");
            }
        })
        
    }

}




function ExcluirDen(id){


    const confirma = confirm("Deseja mesmoe excluir denuncia?");
    if(confirma){
        fetch(`http://localhost:8080/apis/denuncia/${id}`,{
            method:"DELETE",
            headers: { 'Content-Type': 'application/json' },
        })
        .then(res=>{
            if(!res.ok)
                throw new Error("Erro ao excluir denuncia");
            else
            {
                alert("Denuncia excluida");
                carregarDenuncias();
            }
        }).catch(error => alert(error.message));
    }
}

