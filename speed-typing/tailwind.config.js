
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [require("daisyui")],
  daisyui: {
    themes: [
      {
        'dark': {
          "primary": "#ff00b5",
          "secondary": "#00a7ff",
          "accent": "#0091ff",
          "neutral": "#261e06",
          "base-100": "#242424",
          "info": "#0090ff",
          "success": "#00bf6b",
          "warning": "#ffb700",
          "error": "#ff435d",
        }
      },
      {
        'light': {
          "primary": "#0073ff",

          "secondary": "#00c757",

          "accent": "#008500",

          "neutral": "#141a17",

          "base-100": "#f5f5f4",

          "info": "#0085fe",

          "success": "#69ad00",

          "warning": "#fbbf24",

          "error": "#f43f5e",
        }
      },
    ]
  }
}

