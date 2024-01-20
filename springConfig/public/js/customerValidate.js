
const firstName = document.getElementById("validationServer02")
const lastName = document.getElementById("validationServer03")
const fullName = document.getElementById("validationServer04")

function matchName(e) {
	if (e.target.value.match(/^[A-Za-z]+(?:[ _-][A-Za-z]+)*$/) == null || e.target.value.length < 3) {
		e.target.classList.remove('is-valid')
		e.target.classList.add('is-invalid')
	} else {
		e.target.classList.remove('is-invalid')
		e.target.classList.add('is-valid')
	}
}

firstName.addEventListener('change', matchName)
lastName.addEventListener('change', matchName)
firstName.addEventListener('change', () => {
	fullName.value = firstName.value + " " + lastName.value;
})
lastName.addEventListener('change', () => {
	fullName.value = firstName.value + " " + lastName.value;
})

var forms = document.querySelectorAll('.needs-validation')

// Loop over them and prevent submission
Array.prototype.slice.call(forms)
	.forEach(function(form) {
		console.log(form)

		form.addEventListener('submit', function(event) {
			if (!form.checkValidity()) {
				event.preventDefault()
				event.stopPropagation()
			}

			form.classList.add('was-validated')
			form.classList.remove("")
		}, false)
	})



