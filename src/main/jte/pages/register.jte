@import mfa.website.multi_factor_auth.csrf.CsrfHiddenInput
@param Boolean error = false
@param String errorMessage = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <div class="min-h-screen flex items-center justify-center">
        <div class="max-w-md w-full space-y-8 p-8 bg-white rounded-lg shadow-md">
            <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
                Create a new account
            </h2>

            @if(error)
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    <span class="block sm:inline">${errorMessage != null ? errorMessage : "An error occurred during registration"}</span>
                </div>
            @endif

            <!-- Registration Form -->
            <form class="mt-8 space-y-6" id="registerForm">
                ${csrfHiddenInput}
                <div class="rounded-md shadow-sm -space-y-px">
                    <div>
                        <input id="username" name="username" type="text" required
                               class="appearance-none rounded-none block w-full px-3 py-2 border border-gray-300 text-gray-900"
                               placeholder="Username">
                    </div>
                    <div>
                        <input id="password" name="password" type="password" required
                               class="appearance-none rounded-none block w-full px-3 py-2 border border-gray-300 text-gray-900"
                               placeholder="Password">
                    </div>
                    <div>
                        <input id="confirmPassword" name="confirmPassword" type="password" required
                               class="appearance-none rounded-none block w-full px-3 py-2 border border-gray-300 text-gray-900"
                               placeholder="Confirm Password">
                    </div>
                </div>

                <button type="submit"
                        class="w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700">
                    Register
                </button>
            </form>

            <div class="text-center mt-4">
                <p class="text-sm text-gray-600">Already have an account?
                    <a href="/login" class="text-indigo-600 hover:text-indigo-500">Sign in</a>
                </p>
            </div>
        </div>
    </div>

    <script>
        document.getElementById("registerForm").addEventListener("submit", async function(event) {
            event.preventDefault(); // Prevent default form submission

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return;
            }

            const userData = {
                username: username,
                password: password
            };

            try {
                const response = await fetch("/api/auth/register", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(userData)
                });

                const data = await response.json();
                if (response.ok) {
                    alert("Registration successful! Redirecting to login...");
                    window.location.href = "/dashboard";
                } else {
                    alert(data.error || "Registration failed. Please try again.");
                }
            } catch (error) {
                console.error("Error:", error);
                alert("An error occurred during registration");
            }
        });
    </script>
`)
