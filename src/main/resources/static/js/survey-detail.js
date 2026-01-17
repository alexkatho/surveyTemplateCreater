async function loadSurveyDetail() {
    const urlParams = new URLSearchParams(window.location.search);
    const surveyId = urlParams.get('id');
    if (!surveyId) return;

    try {
        const response = await fetch(`/survey/${surveyId}`);
        if (!response.ok) throw new Error('Netzwerk-Fehler');
        
        const survey = await response.json();
        document.getElementById('surveyTitle').innerText = survey.title;
        document.getElementById('surveyInfo').innerText = 
            `Status: ${survey.status}, Start: ${survey.startDate}, Ende: ${survey.endDate}`;

        const questionsList = document.getElementById('questionsList');
        questionsList.innerHTML = '';
        survey.questions.forEach(q => {
            const li = document.createElement('li');
            li.innerText = `${q.position || '-'}: ${q.text} (${q.type})`;
            questionsList.appendChild(li);
        });
    } catch (err) {
        console.error(err);
        alert('Fehler beim Laden des Surveys');
    }
}

loadSurveyDetail();
