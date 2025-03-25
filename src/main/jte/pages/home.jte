@template.layout.default(content = @`
    <div class="min-h-screen flex items-center justify-center">
        <div class="max-w-md w-full space-y-8 p-8 bg-white rounded-lg shadow-md">
            <h1 class="text-4xl font-bold text-center">Welcome</h1>
            <div class="flex justify-center space-x-4">
                <a href="/login"
                   class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                    Login
                </a>
                <a href="/register"
                   class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                    Register
                </a>
            </div>
        </div>
    </div>
`)
