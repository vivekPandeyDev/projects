import axios from "axios";

export function fetchItems() {
  return axios.get("http://localhost:8080/cart")
}

export function addItem(item){
  const data = {
    productId : item.id,
    title: item.title,
    brand: item.brand,
    thumbnail: item.thumbnail,
    price: item.price,
    quantity: 1
  }
  return axios.post("http://localhost:8080/cart",data)
}

export function updateItem(id,itemUpdate){
  return axios.patch(`http://localhost:8080/cart/${id}`,itemUpdate)
}

export function deleteItem(id){
  return axios.delete(`http://localhost:8080/cart/${id}`);
}