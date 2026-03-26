async function login() {

    console.log("Login button clicked");

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    try {

        const response =
            await fetch(
                BASE_URL + "/auth/login",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({
                        email,
                        password
                    })
                }
            );

        if (!response.ok) {

            alert("Invalid email or password");

            return;
        }

        const token =
            await response.text();

        localStorage.setItem(
            "token",
            token
        );

        alert("Login successful");

        const role =
            getUserRole();

        if (role === "AGENCY") {

            window.location.href =
                "agency-dashboard.html";

        } else {

            window.location.href =
                "customer-dashboard.html";

        }

    } catch (error) {

        console.error(error);

        alert("Server error. Please try again.");

    }

}

async function register() {

    const name =
        document.getElementById("name").value;

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    const role =
        document.getElementById("role").value;

    const response =
        await fetch(
            BASE_URL + "/auth/register",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    name,
                    email,
                    password,
                    role
                })
            }
        );

    if (response.ok) {

        alert("Registration successful");

        window.location.href =
            "login.html";

    } else {

        alert("Registration failed");

    }
}