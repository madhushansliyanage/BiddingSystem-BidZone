async function addListing() {

  const userId = parseInt(localStorage.getItem('userid'));
  const username = localStorage.getItem('username');

  console.log("UserId:" +localStorage.getItem('userid')+" "+typeof(userId));
  console.log("username: " + username + " " + typeof (username));

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
    price,
    userId
  };

  console.log("Prepared JSON object: "+ listingData.userId);

  const formData = new FormData();
  formData.append('listingDTO', new Blob([JSON.stringify(listingData)], { type: 'application/json' }));
  formData.append('image', image);

  try {
    const response = await fetch('http://localhost:8080/listing/add?username=' + username, {
      method: 'POST',
      body: formData
    });

    if (response.ok) {
      const result = await response.json();
      alert(`Successfully added Listing`);
      //clearFormData();
      // location.href = '../../HTML/home.html';
    } else {
      const errorText = await response.text();
      alert(`Failed to add Listing: ${errorText}`);
    }
  } catch (error) {
    console.error('Error adding Listing:', error);
    // alert('An error occurred while adding the Listing.');
  }

}