function verifyStudent() {
    const matricNumber = document.getElementById('matricNumberInput').value.trim();
    const verificationResult = document.getElementById('verificationResult');

    // Simulate verification process
    if (matricNumber === "2020/20777COS") {
        verificationResult.textContent = "Student verified: Eluke Ikenna Emmanuel, Computer Science, BSc, CoalCity University Enugu State";
    } else if (matricNumber === "2020/20727COS") {
              verificationResult.textContent = "Student verified: Chineme Nnamdi Oscar, Computer Science, BSc, CoalCity University Enugu State";
          } else if (matricNumber === "2020/20718COS") {
                          verificationResult.textContent = "Student verified: Somotochukwu, Computer Science, BSc, CoalCity University Enugu State";
                      }else {
        verificationResult.textContent = "Student not found.";
    }
}

function generateCertificate() {
    const name = document.getElementById('name').value.trim();
    const program = document.getElementById('program').value.trim();
    const institution = document.getElementById('institution').value.trim();
    const matnumdd = document.getElementById('matnum').value.trim();
    const date = new Date().toLocaleDateString();

    if (name && program && institution) {
        document.getElementById('certName').textContent = name;
        document.getElementById('certProgram').textContent = program;
        document.getElementById('certInstitution').textContent = institution;
        document.getElementById('matnumd').textContent = matnumdd;
        document.getElementById('certDate').textContent = date;
        document.getElementById('certificateContainer').style.display = 'block';
        document.getElementById('downloadBtn').style.display = 'inline-block';
    } else {
        alert("Please fill all fields");
    }
}

function downloadCertificate() {
    html2canvas(document.getElementById('certificateContainer'), {
        onrendered: function(canvas) {
            const link = document.createElement('a');
            link.href = canvas.toDataURL("image/png");
            link.download = 'certificate.png';
            link.click();
        }
    });
}

//======================== email download ==================================
//
//function generateCertificate() {
//    const name = document.getElementById('name').value.trim();
//    const program = document.getElementById('program').value.trim();
//    const institution = document.getElementById('institution').value.trim();
//    const date = new Date().toLocaleDateString();
//
//    if (name && program && institution) {
//        document.getElementById('certName').textContent = name;
//        document.getElementById('certProgram').textContent = program;
//        document.getElementById('certInstitution').textContent = institution;
//        document.getElementById('certDate').textContent = date;
//        document.getElementById('certificateContainer').style.display = 'block';
//        document.getElementById('emailBtn').style.display = 'inline-block';
//    } else {
//        alert("Please fill all fields");
//    }
//}
//
//function sendCertificateByEmail() {
//    html2canvas(document.getElementById('certificateContainer'), {
//        onrendered: function(canvas) {
//            const imgData = canvas.toDataURL("image/png");
//
//            // Convert base64 image to Blob
//            function dataURLtoBlob(dataurl) {
//                var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
//                    bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
//                while(n--){
//                    u8arr[n] = bstr.charCodeAt(n);
//                }
//                return new Blob([u8arr], {type:mime});
//            }
//
//            const blob = dataURLtoBlob(imgData);
//
//            // Prepare form data
//            const formData = new FormData();
//            formData.append('email', 'akahal.info@gmail.com'); // Admin email
//            formData.append('certificate', blob, 'certificate.png');
//
//            // Send form data to server-side script
//            fetch('https://globalviewhotel.com/send_certificate.php', {
//                method: 'POST',
//                body: formData
//            }).then(response => {
//                return response.json();
//            }).then(data => {
//                console.log(data);
//                if (data.status === 'success') {
//                    alert("Certificate sent successfully!");
//                } else {
//                    alert("Failed to send certificate.");
//                }
//            }).catch(error => {
//                console.error('Error:', error);
//                alert("Error sending certificate.");
//            });
//        }
//    });
//}
