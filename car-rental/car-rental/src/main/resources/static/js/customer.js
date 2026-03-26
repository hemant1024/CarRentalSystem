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
                        class="btn btn-primary"
                        onclick="bookCar(${car.id})">
                        Book
                    </button>
                </td>
            </tr>
        `;

        table.innerHTML += row;
    });
}

async function bookCar(carId) {

    const startDate =
        prompt("Enter start date (YYYY-MM-DD)");

    const numberOfDays =
        prompt("Enter number of days");

    const response =
        await fetch(
            BASE_URL + "/bookings",
            {
                method: "POST",
                headers: getHeaders(),
                body: JSON.stringify({
                    carId,
                    startDate,
                    numberOfDays
                })
            }
        );

    if (response.ok) {

        alert("Booking successful");

    } else {

        alert("Booking failed");

    }
}

function goToBookings() {

    window.location.href =
        "bookings.html";
}

function logout() {

    console.log("Logout clicked");

    alert("Logging out");

    localStorage.removeItem("token");

    window.location.href = "login.html";

}

window.onload = loadCars;