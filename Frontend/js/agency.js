async function loadCars() {

    const response =
        await fetch(
            BASE_URL + "/cars",
            {
                method: "GET",
                headers: getHeaders()
            }
        );

    const cars =
        await response.json();

    const table =
        document.getElementById("carTable");

    table.innerHTML = "";

    cars.forEach(car => {

        const row = `
            <tr>
                <td>${car.vehicleModel}</td>
                <td>${car.vehicleNumber}</td>
                <td>${car.seatingCapacity}</td>
                <td>${car.rentPerDay}</td>
                <td>
                    <button
                        class="btn btn-warning"
                        onclick="updateCar(${car.id})">
                        Update
                    </button>

                    <button
                        class="btn btn-danger"
                        onclick="deleteCar(${car.id})">
                        Delete
                    </button>
                </td>
            </tr>
        `;

        table.innerHTML += row;
    });
}

async function addCar() {

    const vehicleModel =
        document.getElementById("model").value;

    const vehicleNumber =
        document.getElementById("number").value;

    const seatingCapacity =
        document.getElementById("seats").value;

    const rentPerDay =
        document.getElementById("rent").value;

    const response =
        await fetch(
            BASE_URL + "/cars",
            {
                method: "POST",
                headers: getHeaders(),
                body: JSON.stringify({
                    vehicleModel,
                    vehicleNumber,
                    seatingCapacity,
                    rentPerDay
                })
            }
        );

    if (response.ok) {

        alert("Car added");

        loadCars();

    } else {

        alert("Failed to add car");

    }
}

async function deleteCar(id) {

    const response =
        await fetch(
            BASE_URL + "/cars/" + id,
            {
                method: "DELETE",
                headers: getHeaders()
            }
        );

    if (response.ok) {

        alert("Car deleted");

        loadCars();

    } else {

        alert("Delete failed");

    }
}

async function updateCar(id) {

    const vehicleModel =
        prompt("New model:");

    const vehicleNumber =
        prompt("New number:");

    const seatingCapacity =
        prompt("New seats:");

    const rentPerDay =
        prompt("New rent:");

    const response =
        await fetch(
            BASE_URL + "/cars/" + id,
            {
                method: "PUT",
                headers: getHeaders(),
                body: JSON.stringify({
                    vehicleModel,
                    vehicleNumber,
                    seatingCapacity,
                    rentPerDay
                })
            }
        );

    if (response.ok) {

        alert("Car updated");

        loadCars();

    } else {

        alert("Update failed");

    }
}

function logout() {

    localStorage.removeItem("token");

    window.location.replace =
        "login.html";
}

window.onload = loadCars;