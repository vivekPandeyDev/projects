console.log("hello validate")

var forms = document.querySelectorAll('.needs-validation')
var addresss = document.querySelectorAll('.address-group')

var texts = document.querySelectorAll("input[type='text']")

var selects = document.querySelectorAll("select")



Array.prototype.slice.call(addresss)
	.forEach(function(address) {

		address.addEventListener('change', function(e) {
			let myAddress = address.children
			let finalValue = "House Number -"
			for (let i = 1; i < myAddress.length - 1; i++) {
				if (address.children[i].querySelector('input') != null) {
					finalValue += address.children[i].querySelector('input').value + ", ";
				} else {
					finalValue += address.children[i].querySelector('select')?.value + ", ";
				}

			}
			address.children[myAddress.length - 1].querySelector('textarea').value = finalValue;
		})

	})


// Loop over them and prevent submission
Array.prototype.slice.call(forms)
	.forEach(function(form) {
		form.addEventListener('submit', function(event) {
			let isValidText = true
			let isSelected = true
			Array.prototype.slice.call(texts)
				.forEach(function(text) {
					if (isAlphabets(text.value) == null) {
						isValidText = false;
						text.classList.add('is-invalid')
						text.classList.remove('is-valid')

					} else {
						text.classList.remove('is-invalid')
						text.classList.add('is-valid')
					}
				})

			Array.prototype.slice.call(selects)
				.forEach(function(select) {
					if (!isFieldSelected(select.value)) {
						isSelected = false
						select.classList.add("is-invalid")
						select.classList.remove('is-valid')
					} else {
						select.classList.remove('is-invalid')
						select.classList.add('is-valid')
					}

				})

			console.log("isAlphabets:", isValidText, "is alpahbet", isSelected)

			if (!form.checkValidity() || !isValidText || !isSelected) {
				event.preventDefault()
				event.stopPropagation()

			}
			form.classList.add('was-validated')

		}, false)
	})


function isAlphabets(str) {
	if (str.trim().length == 0) {
		return null
	}
	return str.match(/^[A-Za-z ]+$/)
}


function isFieldSelected(str) {
	return "none" !== str
}
