// Khi nội dung file html đã được hiển thị trên browser thì sẽ kích hoạt
$(document).ready(function(){
    
    // Đăng ký sự kiện click cho thẻ tag được chỉ định bên HTML
    $("#btn-sign-in").click(function(){
        // .val() : Lấy giá trị của thẻ input được chỉ định
        var username = $("#user").val()
        var password = $("#pass").val()

        // Xuất giá trị ra trên tab console trên trình duyệt
        console.log("username : ",username, " password : ",password);

        //ajax : Dùng để call ngầm API mà không cần trình duyệt
        //axios, fetch
        //data : chỉ có khi tham số truyền ngầm
        $.ajax({
            url: "http://localhost:8080/login/signin",
            method: "post",
            data: {
                email: username,
                password: password
            }
        }).done(function(data){

            if(data && data.statusCode == 200) {
                localStorage.setItem("token", data.data)

                 window.location='index.html'

            } else{
                alert("Sai email hoặc mật khẩu.")
            }
            console.log("server tra ve ", data)

            window.location.href = 'index.html';
        })
    })

    //  Xử lý đăng kí

    $("#btn-sign-up").click(function(){
            var username = $("#user-sign-up").val()
            var password = $("#pass-sign-up").val()
            var repassword = $("#re-pass-sign-up").val()
            var email = $("#email-sign-up").val()

            console.log("username : ", username, " password : ", password, " repassword ", repassword, " email ", email );

            // Thực hiện kiểm tra hợp lý trước khi gửi yêu cầu đăng ký
            if (username && password && repassword && email) {
                if (password === repassword) {
                    $.ajax({
                        url: "http://localhost:8080/login/signup",
                        method: "post",
                        contentType: "application/json",
                        data: JSON.stringify({
                            userName: username,
                            password: password,
                            email: email
                        })
                    }).done(function(data) {
                        if(data && data.statusCode == 200) {
                            var token = data.data;
                            var userId = getUserIdFromToken(token);  // Gọi hàm để lấy ID người dùng từ token
                            localStorage.setItem("token", token);
                            localStorage.setItem("userId", userId);
                            window.location.href = 'index.html'; // Chuyển hướng sau khi đăng ký thành công
                        } else {
                            alert("Sai email hoặc mật khẩu.");
                        }
                    });
                } else {
                    alert("Mật khẩu không khớp. Vui lòng kiểm tra lại.");
                }
            } else {
                alert("Vui lòng nhập đầy đủ thông tin.");
            }
        })

    })