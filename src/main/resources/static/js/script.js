const concerts = [
    { id: 1, name: "Concert 1", imageUrl: "/images/1.jpg" },
    { id: 2, name: "Concert 2", imageUrl: "/images/2.jpg" },
    { id: 3, name: "Concert 3", imageUrl: "/images/3.jpg" },
    { id: 4, name: "Concert 4", imageUrl: "/images/4.jpg" },
    { id: 5, name: "Concert 5", imageUrl: "/images/5.jpg" },
    { id: 6, name: "Concert 6", imageUrl: "/images/6.jpg" },
    { id: 7, name: "Concert 7", imageUrl: "/images/7.jpg" },
    { id: 8, name: "Concert 8", imageUrl: "/images/8.jpg" },
    { id: 9, name: "Concert 9", imageUrl: "/images/9.jpg" },
    { id: 10, name: "Concert 10", imageUrl: "/images/10.jpg" },
    { id: 11, name: "Concert 11", imageUrl: "/images/11.jpg" },
    { id: 12, name: "Concert 12", imageUrl: "/images/12.jpg" },
    { id: 13, name: "Concert 13", imageUrl: "/images/13.jpg" },
    { id: 14, name: "Concert 14", imageUrl: "/images/14.jpg" },
    { id: 15, name: "Concert 15", imageUrl: "/images/15.jpg" },
    { id: 16, name: "Concert 16", imageUrl: "/images/16.jpg" },
    { id: 17, name: "Concert 17", imageUrl: "/images/17.jpg" },
    { id: 22, name: "Concert 22", imageUrl: "/images/22.jpg" },
    { id: 23, name: "Concert 13", imageUrl: "/images/23.jpg" }
];

// Function to dynamically generate carousel items
function generateCarouselItems() {
    const carouselInner = document.getElementById("carousel-inner");
    concerts.forEach(concert => {
        const item = document.createElement("div");
        item.classList.add("carousel-item");
        const image = document.createElement("img");
        image.src = concert.imageUrl;
        image.alt = concert.name;
        image.addEventListener('click', () => {
            console.log(`Redirecting to /details/${concert.id}`);
            window.location.href = `/details/${concert.id}`;
        });
        item.appendChild(image);
        carouselInner.appendChild(item);
    });
}


let currentSlide = 0;
function prevSlide() {
    currentSlide = (currentSlide - 1 + concerts.length) % concerts.length;
    updateCarousel();
}

function nextSlide() {
    currentSlide = (currentSlide + 1) % concerts.length;
    updateCarousel();
}

function updateCarousel() {
    const carouselInner = document.getElementById("carousel-inner");
    const newPosition = -currentSlide * 210;
    carouselInner.style.transform = `translateX(${newPosition}px)`;
}
generateCarouselItems();
