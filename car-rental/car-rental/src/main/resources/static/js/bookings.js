async function loadBookings() {

    const response =
        await fetch(
            BASE_URL + "/bookings",
            {
                method: "GET",
                headers: getHeaders()
            }
        );

    const bookings =
        await response.json();

    const table =
        document.getElementById("bookingTable");

    table.innerHTML = "";

    bookings.forEach(b => {

        const row = `
            <tr>
                <td>${b.vehicleModel}</td>
                <td>${b.startDate}</td>
                <td>${b.numberOfDays}</td>
                <td>${b.totalCost}</td>
                <td>
                    <button
                        class="btn btn-danger"
                        onclick="cancelBooking(${b.bookingId})">
                        Cancel
                    </button>
                </td>
            </tr>
        `;

        table.innerHTML += row;
    });
}

async function cancelBooking(id) {

    const response =
        await fetch(
            BASE_URL + "/bookings/" + id,
            {
                method: "DELETE",
                headers: getHeaders()
            }
        );

    if (response.ok) {

        alert("Booking cancelled");

        loadBookings();

    } else {

        alert("Cancel failed");

    }
}

function goBack() {

    window.location.href =
        "customer-dashboard.html";
}

window.onload = loadBookings;