import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

import Book from "../components/Book.vue"
import addBook from "../components/addBook.vue";
import BookDetails from "../components/bookDetails.vue";
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'index',
      component: Book
    },
    {
      path: '/book',
      name: 'book',
      component: Book
    },
    {
      path: '/addBook',
      name: 'addBook',
      component: addBook
    },
    {
      path: '/bookDetails',
      name: 'bookDetails',
      component: BookDetails
    }
  ]
})
