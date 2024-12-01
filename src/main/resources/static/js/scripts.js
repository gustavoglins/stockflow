// HOME | FAQ - Toggle content visibility and update styles
function toggleFaqContent(header) {
    const content = header.nextElementSibling;
    const arrow = header.querySelector('.arrow');
    const faqCard = header.closest('.faq-card'); // Get the FAQ card container

    if (content.style.display === 'block') {
        content.style.display = 'none';
        arrow.classList.remove('expanded');
        faqCard.classList.remove('expanded'); // Remove expanded class to reset background
    } else {
        content.style.display = 'block';
        arrow.classList.add('expanded');
        faqCard.classList.add('expanded'); // Add expanded class to apply background effect
    }
}



// HEADER - Show the header when scrolling up
let lastScrollTop = 0; // Guarda a última posição do scroll

window.addEventListener('scroll', function () {
    const header = document.querySelector('.header');
    let currentScroll = window.pageYOffset || document.documentElement.scrollTop;

    // Se rolou para baixo, esconde o header
    if (currentScroll > lastScrollTop && currentScroll > 10) {
        header.classList.add('hidden');
    } else {
        // Se rolou para cima, mostra o header
        if (currentScroll < lastScrollTop) {
            header.classList.remove('hidden');
        }
    }
    lastScrollTop = currentScroll <= 0 ? 0 : currentScroll; // Para não ter valores negativos
});



// DASHBOARD, INVENTORY | DATE - Return the actual date

const currentDate = new Date();

const options = { year: 'numeric', month: 'long', day: 'numeric' };
const formattedDate = currentDate.toLocaleDateString('en-US', options);

document.querySelector('.main-content__date').textContent = formattedDate;