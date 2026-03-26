const BASE_URL = "/api";

function getToken() {
    return localStorage.getItem("token");
}

function checkAuth() {

    const token =
        localStorage.getItem("token");

    if (!token) {

        window.location.href =
            "login.html";

    }
}

function getHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + getToken()
    };
}

function getUserRole() {

    const token =
        localStorage.getItem("token");

    if (!token) return null;

    const payload =
        JSON.parse(
            atob(
                token.split(".")[1]
            )
        );

    return payload.role;
}