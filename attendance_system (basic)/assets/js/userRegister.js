<<<<<<< HEAD
$(document).ready(function () {

  
    
  var myModal = new bootstrap.Modal(
    document.getElementById("attendance-alert"),
    {
      keyboard: true,
    }
  );


  $(".dismiss").click(() => {
    myModal.hide();
  });

  $("#user-register").submit(function (e) {
    e.preventDefault();
    var formData = new FormData(e.target);
    let data = Object.fromEntries(formData);
    const { user_name, email, description, phoneNo } = data;
    if (user_name && email && description && phoneNo) {
      console.log(JSON.stringify(data));

      $.ajax({
        type: "POST",
        url: "http://10.0.61.27:8080/users",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (data) {
          console.log(data);
          myModal.show();
          $(".modal-title").text("user register successfully");
          $(".modal-body > p").text(data.message);
        },
        error: function (data) {
          console.log(data);
          myModal.show();
          $(".modal-title").text("user register failed");
          $(".modal-body > p").text(JSON.parse(data.responseText).message);
          JSON.parse(data.responseText).errors.forEach((data) =>
            $(".modal-body > p").append(`<br> ${data}`)
          );
        },
      });
    } else {
        myModal.show();
        $(".modal-title").text("cannot register user");
        $(".modal-body > p").text("required all the fileds");       
    }
  });
});
=======
$(document).ready(function () {

  
    
  var myModal = new bootstrap.Modal(
    document.getElementById("attendance-alert"),
    {
      keyboard: true,
    }
  );


  $(".dismiss").click(() => {
    myModal.hide();
  });

  $("#user-register").submit(function (e) {
    e.preventDefault();
    var formData = new FormData(e.target);
    let data = Object.fromEntries(formData);
    const { user_name, email, description, phoneNo } = data;
    if (user_name && email && description && phoneNo) {
      console.log(JSON.stringify(data));

      $.ajax({
        type: "POST",
        url: "http://10.0.61.27:8080/users",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (data) {
          console.log(data);
          myModal.show();
          $(".modal-title").text("user register successfully");
          $(".modal-body > p").text(data.message);
        },
        error: function (data) {
          console.log(data);
          myModal.show();
          $(".modal-title").text("user register failed");
          $(".modal-body > p").text(JSON.parse(data.responseText).message);
          JSON.parse(data.responseText).errors.forEach((data) =>
            $(".modal-body > p").append(`<br> ${data}`)
          );
        },
      });
    } else {
        myModal.show();
        $(".modal-title").text("cannot register user");
        $(".modal-body > p").text("required all the fileds");       
    }
  });
});
>>>>>>> test
