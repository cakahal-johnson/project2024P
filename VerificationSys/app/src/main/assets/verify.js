function verifyCertificate() {
    const fileInput = document.getElementById('certificateInput');
    const verificationResult = document.getElementById('certificateVerificationResult');

    if (fileInput.files.length === 0) {
        verificationResult.textContent = "Please upload a certificate file.";
        verificationResult.style.color = "red";
        return;
    }

    const file = fileInput.files[0];
    const reader = new FileReader();

    reader.onload = function(e) {
        const content = e.target.result;
        console.log(content); // Log the file content to the console for debugging
        if (content.includes("CoalCity University Enugu State")) {
            verificationResult.textContent = "Certificate verified.";
            verificationResult.style.color = "green";
        } else {
            verificationResult.textContent = "Certificate not recognized.";
            verificationResult.style.color = "red";
        }
    };

    reader.onerror = function(e) {
        console.error("Error reading file:", e);
        verificationResult.textContent = "Error reading file. Please try again.";
        verificationResult.style.color = "red";
    };

    reader.readAsText(file);
}

function verifyStudent() {
    const matricNumber = document.getElementById('matricNumberInput').value.trim();
    const verificationResult = document.getElementById('studentVerificationResult');

    // Simulate verification process
    if (matricNumber === "2020/20777COS") {
        verificationResult.textContent = "Student verified: Eluke Ikenna Emmanuel, Computer Science, BSc, CoalCity University Enugu State";
        verificationResult.style.color = "green";
    } else if (matricNumber === "2020/20727COS") {
        verificationResult.textContent = "Student verified: Chineme Nnamdi Oscar, Computer Science, BSc, CoalCity University Enugu State";
        verificationResult.style.color = "green";
    } else if (matricNumber === "2020/20718COS") {
        verificationResult.textContent = "Student verified: Somotochukwu, Computer Science, BSc, CoalCity University Enugu State";
        verificationResult.style.color = "green";
    } else {
        verificationResult.textContent = "Student not found.";
        verificationResult.style.color = "red";
    }
}

