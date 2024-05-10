
async function addListing() {
    const name = document.getElementById('name').value.trim();
    const description = document.getElementById('description').value.trim();
    const category = document.getElementById('category').value.trim();
    const ending = document.getElementById('ending').value.trim();
    const price = document.getElementById('price').value.trim();
    const fileInput = document.getElementById('file');
    const image = fileInput.files[0];
  
    if (!name || !description || !category || !ending || !price || !image) {
      alert('Please fill out all required fields.');
      return;
    }
    if (localStorage.getItem('username') == null) {
      alert('Please Login.');
      return;
    }
  
    const listingData = {
      name,
      description,
      category,
      ending,
      price
    };
  
    const formData = new FormData();
    formData.append('listingDTO', new Blob([JSON.stringify(listingData)], { type: 'application/json' }));
    formData.append('image', image);
  
    const username = localStorage.getItem('username');
  
    try {
      const response = await fetch('http://localhost:8080/listing/add?username=' + username, {
        method: 'POST',
        body: formData
      });
      

      if (response.ok) {
        const result = await response.json();
        alert(`Successfully added Listing`);
        clearFormData();
        location.reload();
      } else {
        const errorText = await response.text();
        alert(`Failed to add Listing: ${errorText}`);
      }
    } catch (error) {
      // console.error('Error adding Listing:', error);
      // alert('An error occurred while adding the Listing.');
    }
    
  }
  
  