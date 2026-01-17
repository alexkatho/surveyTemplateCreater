let questionCount = 0;

document.getElementById('addQuestionBtn').addEventListener('click', addQuestion);
document.getElementById('submitBtn').addEventListener('click', submitSurvey);

function addQuestion() {
    questionCount++;
    const container = document.getElementById('questionsContainer');

    const div = document.createElement('div');
    div.className = 'question';
    div.id = `question-${questionCount}`;

    div.innerHTML = `
        <label>Frage Text:</label>
        <input type="text" class="q-text">

        <label>Frage Typ:</label>
        <select class="q-type">
            <option value="TEXT">TEXT</option>
            <option value="NUMERIC">NUMERIC</option>
            <option value="SINGLE_CHOICE">SINGLE_CHOICE</option>
            <option value="MULTIPLE_CHOICE">MULTIPLE_CHOICE</option>
        </select>

        <label>Erforderlich?</label>
        <select class="q-required">
            <option value="true">Ja</option>
            <option value="false">Nein</option>
        </select>

        <label>Position:</label>
        <input type="number" class="q-position" value="${questionCount}">

        <div class="options-container" style="display:none;"></div>
        <button type="button" class="add-option-btn" style="display:none;">Antwortoption hinzufügen</button>

        <button type="button" class="remove-question-btn">Frage entfernen</button>
    `;

    container.appendChild(div);

    const typeSelect = div.querySelector('.q-type');
    const addOptionBtn = div.querySelector('.add-option-btn');
    const removeBtn = div.querySelector('.remove-question-btn');

    typeSelect.addEventListener('change', () => onTypeChange(div));
    addOptionBtn.addEventListener('click', () => addOption(div));
    removeBtn.addEventListener('click', () => div.remove());
}

function onTypeChange(questionDiv) {
    const type = questionDiv.querySelector('.q-type').value;
    const optionsContainer = questionDiv.querySelector('.options-container');
    const addBtn = questionDiv.querySelector('.add-option-btn');

    if (type === 'SINGLE_CHOICE' || type === 'MULTIPLE_CHOICE') {
        optionsContainer.style.display = 'block';
        addBtn.style.display = 'inline-block';

        if (optionsContainer.children.length === 0) {
            addOption(questionDiv);
            addOption(questionDiv);
        }
    } else {
        optionsContainer.innerHTML = '';
        optionsContainer.style.display = 'none';
        addBtn.style.display = 'none';
    }
}

function addOption(questionDiv) {
    const optionsContainer = questionDiv.querySelector('.options-container');
    const optionIndex = optionsContainer.children.length + 1;

    const div = document.createElement('div');
    div.className = 'option';

    div.innerHTML = `
        <input type="text" class="opt-text" placeholder="Antwortoption">
        <input type="number" class="opt-position" value="${optionIndex}" style="width:60px;">
        <button type="button">✖</button>
    `;

    div.querySelector('button').addEventListener('click', () => div.remove());
    optionsContainer.appendChild(div);
}

function validateSurvey(survey) {
    if (!survey.title || survey.title.trim().length < 3) {
        return 'Titel muss mindestens 3 Zeichen haben.';
    }

    if (!survey.startDate || !survey.endDate) {
        return 'Start- und Enddatum müssen gesetzt sein.';
    }

    if (survey.endDate < survey.startDate) {
        return 'Enddatum darf nicht vor Startdatum liegen.';
    }

    if (!survey.questions || survey.questions.length === 0) {
        return 'Mindestens eine Frage ist erforderlich.';
    }

    for (let i = 0; i < survey.questions.length; i++) {
        const q = survey.questions[i];

        if (!q.text || q.text.trim().length < 3) {
            return `Frage ${i + 1}: Text muss mindestens 3 Zeichen haben.`;
        }

        if (q.type === 'SINGLE_CHOICE' || q.type === 'MULTIPLE_CHOICE') {
            if (!q.options || q.options.length < 2) {
                return `Frage ${i + 1}: Mindestens zwei Antwortoptionen erforderlich.`;
            }

            for (let j = 0; j < q.options.length; j++) {
                if (!q.options[j].label || q.options[j].label.trim().length === 0) {
                    return `Frage ${i + 1}, Option ${j + 1}: Text darf nicht leer sein.`;
                }
            }
        }
    }

    return null;
}

async function submitSurvey() {
    const errorBox = document.getElementById('errorBox');
    errorBox.textContent = '';

    const survey = {
        title: document.getElementById('title').value,
        description: document.getElementById('description').value,
        startDate: document.getElementById('startDate').value,
        endDate: document.getElementById('endDate').value,
        status: document.getElementById('status').value,
        questions: []
    };

    document.querySelectorAll('.question').forEach((div) => {
        const type = div.querySelector('.q-type').value;

        const q = {
            text: div.querySelector('.q-text').value,
            type: type,
            required: div.querySelector('.q-required').value === 'true',
            position: parseInt(div.querySelector('.q-position').value)
        };

        if (type === 'SINGLE_CHOICE' || type === 'MULTIPLE_CHOICE') {
            q.options = [];
            div.querySelectorAll('.option').forEach((optDiv) => {
                const text = optDiv.querySelector('.opt-text').value;

                q.options.push({
                    label: text,
                    value: text,   // 🔥 Backend braucht value → setzen wir identisch
                    position: parseInt(optDiv.querySelector('.opt-position').value)
                });
            });
        }

        survey.questions.push(q);
    });

    const validationError = validateSurvey(survey);
    if (validationError) {
        errorBox.textContent = validationError;
        return;
    }

    try {
        const response = await fetch('/survey', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(survey)
        });

        if (!response.ok) {
            const err = await response.text();
            throw new Error(err);
        }

        const created = await response.json();
        alert(`Survey erstellt! ID: ${created.id}`);
        window.location.href = '/';
    } catch (err) {
        console.error(err);
        errorBox.textContent = 'Fehler beim Erstellen: ' + err.message;
    }
}
