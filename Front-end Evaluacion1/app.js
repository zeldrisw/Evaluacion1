const API = "http://localhost:8080/api/vehiculos";

let idEditar = null;

// CREAR O ACTUALIZAR
function guardarVehiculo() {

    const vehiculo = {
        modelo: document.getElementById("modelo").value,
        categoria: document.getElementById("categoria").value,
        descripcion: document.getElementById("descripcion").value,
        precioPorDia: parseFloat(
            document.getElementById("precio").value
        ),
        unidadesDisponibles: parseInt(
            document.getElementById("unidades").value
        )
    };

    let metodo = "POST";
    let url = API;

    if (idEditar !== null) {
        metodo = "PUT";
        url = `${API}/${idEditar}`;
    }

    fetch(url, {
        method: metodo,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(vehiculo)
    })
        .then(response => response.json())
        .then(() => {

            alert(
                metodo === "POST"
                    ? "Vehículo registrado correctamente"
                    : "Vehículo actualizado correctamente"
            );

            limpiarFormulario();

            idEditar = null;

            listarVehiculos();
        })
        .catch(error => console.error(error));
}

// LISTAR TODOS
function listarVehiculos() {

    fetch(API)
        .then(response => response.json())
        .then(data => {

            const tabla =
                document.getElementById("tablaVehiculos");

            tabla.innerHTML = "";

            data.forEach(v => {

                tabla.innerHTML += `
                <tr>
                    <td>${v.id}</td>
                    <td>${v.modelo}</td>
                    <td>${v.categoria}</td>
                    <td>$${v.precioPorDia}</td>
                    <td>${v.unidadesDisponibles}</td>

                    <td>
                        <button onclick="editarVehiculo(${v.id})">
                            Editar
                        </button>

                        <button onclick="eliminarVehiculo(${v.id})">
                            Eliminar
                        </button>
                    </td>
                </tr>
                `;
            });
        });
}

// BUSCAR POR ID
function buscarPorId() {

    const id =
        document.getElementById("buscarId").value;

    if (id === "") {
        alert("Ingrese un ID");
        return;
    }

    fetch(`${API}/${id}`)
        .then(response => response.json())
        .then(v => {

            document.getElementById(
                "resultadoBusqueda"
            ).innerHTML = `
            
            <h3>Resultado</h3>

            <p><strong>ID:</strong> ${v.id}</p>
            <p><strong>Modelo:</strong> ${v.modelo}</p>
            <p><strong>Categoría:</strong> ${v.categoria}</p>
            <p><strong>Precio:</strong> $${v.precioPorDia}</p>
            <p><strong>Unidades:</strong> ${v.unidadesDisponibles}</p>

            `;
        })
        .catch(() => {

            document.getElementById(
                "resultadoBusqueda"
            ).innerHTML =
                "<p>Vehículo no encontrado</p>";
        });
}

// CARGAR DATOS PARA EDITAR
function editarVehiculo(id) {

    fetch(`${API}/${id}`)
        .then(response => response.json())
        .then(v => {

            document.getElementById("modelo").value =
                v.modelo;

            document.getElementById("categoria").value =
                v.categoria;

            document.getElementById("descripcion").value =
                v.descripcion;

            document.getElementById("precio").value =
                v.precioPorDia;

            document.getElementById("unidades").value =
                v.unidadesDisponibles;

            idEditar = v.id;

            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });

        });
}

// ELIMINAR
function eliminarVehiculo(id) {

    if (!confirm("¿Desea eliminar este vehículo?")) {
        return;
    }

    fetch(`${API}/${id}`, {
        method: "DELETE"
    })
        .then(() => {

            alert("Vehículo eliminado");

            listarVehiculos();
        });
}

// LIMPIAR FORMULARIO
function limpiarFormulario() {

    document.getElementById("modelo").value = "";
    document.getElementById("categoria").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("precio").value = "";
    document.getElementById("unidades").value = "";
}

// AL CARGAR LA PÁGINA
window.onload = listarVehiculos;