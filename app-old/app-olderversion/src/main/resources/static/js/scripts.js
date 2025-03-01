document.addEventListener("DOMContentLoaded", function () {
    console.log("Student Attendance App Loaded!");

    const studentForm = document.getElementById("studentForm");
    if (studentForm) {
        studentForm.addEventListener("submit", function (event) {
            event.preventDefault();
            let studentName = document.getElementById("studentName").value.trim();

            if (studentName === "") {
                alert("Student name cannot be empty!");
                return;
            }

            fetch("/addStudent", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name: studentName }),
            })
            .then(response => {
                if (!response.ok) throw new Error("Failed to add student");
                return response.text();
            })
            .then(() => location.reload()) // Reload to update student list
            .catch(error => {
                console.error("Error adding student:", error);
                alert("Error: Unable to add student");
            });
        });
    }
});

function toggleAttendance(studentId) {
    fetch(`/updateAttendance/${studentId}`, { method: "PUT" }) // Use PUT instead of POST
        .then(response => {
            if (!response.ok) throw new Error("Failed to update attendance");
            return response.text();
        })
        .then(() => {
            let statusElement = document.getElementById(`attendance-status-${studentId}`);
            if (statusElement) {
                statusElement.textContent = statusElement.textContent === "Present" ? "Absent" : "Present";
            }
        })
        .catch(error => {
            console.error("Error updating attendance:", error);
            alert("Error: Unable to update attendance");
        });
}
