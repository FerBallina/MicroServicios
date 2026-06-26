// Configuración
const API_URL = 'http://localhost:8081/consultarFecha';

// Elementos del DOM
const fechaInput = document.getElementById('fecha');
const cantidadDiasInput = document.getElementById('cantidad-dias');
const consultarBtn = document.getElementById('consultar-btn');

// Event Listeners
consultarBtn.addEventListener('click', consultar);

// Permitir enviar con Enter
fechaInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') consultar();
});

cantidadDiasInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') consultar();
});

async function consultar() {
    // Validar que la fecha esté ingresada
    if (!fechaInput.value) {
        mostrarError('Por favor ingrese una fecha');
        return;
    }

    const fecha = fechaInput.value; // Formato: YYYY-MM-DD
    const cantidadDias = parseInt(cantidadDiasInput.value) || 0;

    // Deshabilitar el botón mientras se procesa
    consultarBtn.disabled = true;
    consultarBtn.textContent = 'Consultando...';

    try {
        // Convertir fecha de DD/MM/YYYY a YYYY-MM-DD si es necesario
        const fechaFormato = convertirFecha(fecha);

        const payload = {
            fecha: fechaFormato,
            cantidadDias: cantidadDias
        };

        console.log('Enviando payload:', payload);

        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const data = await response.json();
        console.log('Respuesta del servidor:', data);

        // Mostrar resultado
        if (data.esFeriado) {
            mostrarResultado(true, data.nombre || 'Feriado');
        } else {
            mostrarResultado(false);
        }

    } catch (error) {
        console.error('Error:', error);
        mostrarError(`Error al consultar: ${error.message}`);
    } finally {
        consultarBtn.disabled = false;
        consultarBtn.textContent = 'Consultar';
    }
}

function convertirFecha(fecha) {
    // Si es en formato YYYY-MM-DD (del input type="date"), devolver como está
    if (fecha.match(/^\d{4}-\d{2}-\d{2}$/)) {
        return fecha;
    }

    // Si es en formato DD/MM/YYYY, convertir a YYYY-MM-DD
    if (fecha.match(/^\d{2}\/\d{2}\/\d{4}$/)) {
        const [dia, mes, año] = fecha.split('/');
        return `${año}-${mes}-${dia}`;
    }

    return fecha;
}

function mostrarResultado(esFeriado, nombreFeriado = '') {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.style.display = 'block';

    let contenido = '';
    if (esFeriado) {
        contenido = `
            <div class="modal-content feriado">
                <div class="icon">🎉</div>
                <h2>¡Es Feriado!</h2>
                <p>${nombreFeriado}</p>
                <div class="modal-buttons">
                    <button class="btn-ok" onclick="cerrarModal(this.parentElement.parentElement.parentElement)">Aceptar</button>
                </div>
            </div>
        `;
    } else {
        contenido = `
            <div class="modal-content no-feriado">
                <div class="icon">📅</div>
                <h2>No es Feriado</h2>
                <p>La fecha no corresponde a un feriado</p>
                <div class="modal-buttons">
                    <button class="btn-ok" onclick="cerrarModal(this.parentElement.parentElement.parentElement)">Aceptar</button>
                </div>
            </div>
        `;
    }

    modal.innerHTML = contenido;
    document.body.appendChild(modal);

    // Cerrar modal al hacer clic fuera del contenido
    modal.addEventListener('click', (e) => {
        if (e.target === modal) {
            cerrarModal(modal);
        }
    });
}

function cerrarModal(modal) {
    if (modal && modal.parentElement) {
        modal.remove();
    }
}

function mostrarError(mensaje) {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.style.display = 'block';

    const contenido = `
        <div class="modal-content">
            <div class="icon">⚠️</div>
            <h2>Error</h2>
            <p>${mensaje}</p>
            <div class="modal-buttons">
                <button class="btn-ok" onclick="cerrarModal(this.parentElement.parentElement.parentElement)">Cerrar</button>
            </div>
        </div>
    `;

    modal.innerHTML = contenido;
    document.body.appendChild(modal);

    // Cerrar modal al hacer clic fuera del contenido
    modal.addEventListener('click', (e) => {
        if (e.target === modal) {
            cerrarModal(modal);
        }
    });
}
