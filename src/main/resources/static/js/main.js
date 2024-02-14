const domParser = new DOMParser()

const observeForm = (formId) => {
    let formChanged = false;
    const form = document.getElementById(formId)

    // Attach event listeners to form elements
    form.addEventListener('input', function (event) {
        // Set the formChanged flag when any input or textarea changes
        formChanged = true;
    });

    // Optionally, you can reset the formChanged flag when the form is submitted
    form.addEventListener('submit', function () {
        formChanged = false;
    });

    // Listen for the beforeunload event
    window.addEventListener('beforeunload', function (event) {
        if (formChanged) {
            // Display a confirmation message
            const message = 'You have unsaved changes. Are you sure you want to leave?';
            event.returnValue = message; // Standard for most browsers
            return message; // For some older browsers
        }
    });
}

const previewImage = (event) => {
    /**
     * Get the selected files.
     */
    const imageFiles = event.target.files;
    /**
     * Count the number of files selected.
     */
    const imageFilesLength = imageFiles.length;
    /**
     * If at least one image is selected, then proceed to display the preview.
     */
    if (imageFilesLength > 0) {
        /**
         * Get the image path.
         */
        const imageSrc = URL.createObjectURL(imageFiles[0]);
        /**
         * Select the image preview element.
         */
        const imagePreviewElement = document.querySelector("#preview-selected-image");
        /**
         * Assign the path to the image preview element.
         */
        imagePreviewElement.src = imageSrc;
    }
};

const searchInput = document.getElementById('search-input')
searchInput.addEventListener('keyup', (e) => {
    if (e.key === 'Enter') {
        window.location.href = window.location.pathname + '?searchQuery=' + encodeURIComponent(searchInput.value);
    }

    return false;
})

const shimmerTitle = document.getElementById('title-shimmer')
const contentShimmers = document.getElementById('content-shimmers')
const blogTitleEl = document.getElementById('blog-title')
const blogContentEl = document.getElementById('blog-content')
const noContentEl = document.getElementById('no-content-found')

const on_blog_click = async (id) => {
    showShimmer(true)
    const response = await fetch(`/blog/get-blog?blogId=${id}`)
    const html = await response.text()
    const doc = domParser.parseFromString(html, 'text/html')
    showShimmer(false)
    blogTitleEl.textContent = doc.getElementById('blog-title').value
    blogContentEl.textContent = doc.getElementById('blog-content').value

    const listItems = document.getElementsByClassName('list-item')
    for (let i = 0; i < listItems.length; i++) {
        const item = listItems[i]
        item.classList.remove('active')
    }

    const listItem = document.getElementById(`blog_item_${id}`)
    listItem.classList.toggle('active')
}

const showShimmer = (shouldShow = true) => {
    noContentEl.style.display = 'none'
    if (shouldShow) {
        shimmerTitle.style.display = 'block'
        contentShimmers.style.display = 'flex'
        blogTitleEl.style.display = 'none'
        blogContentEl.style.display = 'none'
    } else {
        shimmerTitle.style.display = 'none'
        contentShimmers.style.display = 'none'
        blogTitleEl.style.display = 'block'
        blogContentEl.style.display = 'block'
    }
}
