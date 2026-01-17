async function loadSurveys() {
    try {
        const response = await fetch('/survey?page=0&size=10');
        if (!response.ok) throw new Error('Netzwerk-Fehler');
        
        const data = await response.json();
        const tbody = document.querySelector('#surveyTable tbody');
        tbody.innerHTML = '';

        data.content.forEach(survey => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${survey.id}</td>
                <td>${survey.title}</td>
                <td>${survey.startDate}</td>
                <td>${survey.endDate}</td>
                <td><button onclick="viewDetails(${survey.id})">Details</button></td>
            `;
            tbody.appendChild(tr);
        });
    } catch (err) {
        console.error(err);
        alert('Fehler beim Laden der Surveys');
    }
}

function viewDetails(id) {
    window.location.href = `/survey-detail.html?id=${id}`;
}

loadSurveys();
