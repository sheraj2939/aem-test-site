/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [],
  theme: {
    extend: {},
  },
  purge: ['./src/**/*.html', './src/**/*.js'],
  plugins:  [
    require('tailwindcss'),
    require('autoprefixer')
  ],
}

