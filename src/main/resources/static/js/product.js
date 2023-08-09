function confirmDelete(productId) {
  if (confirm("Are you sure you want to delete this product?")) {
    // To display the productId in the console
    console.log(productId);
    fetch("/store_inventory_management/product/delete/" + productId, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.success) {
          // Remove the deleted row from the table
          const row = document
            .querySelector(`[data-product-id="${productId}"]`)
            .closest("tr");
//          row.remove();
          alert("Product deleted successfully.");
        } else {
          alert("Failed to delete the product.");
        }
      })
      .catch((error) => {
        console.error("Error deleting product:", error);
      });
  }
}
