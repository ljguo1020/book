<template>
  <div class="book-details">
    <el-form label-position="right" label-width="80px" :model="book">
      <el-form-item label="书名">
        <el-input v-model="book.title" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="作者">
        <el-input v-model="book.author" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-input v-model="book.category" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="链接">
        <el-input v-model="book.url" :disabled="true">
          <template slot="prepend">http://47.109.95.126:8080/</template>
          <el-button slot="append" icon="el-icon-copy-document" @click="copyUrl(book.url)"></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="book.description" type="textarea" :disabled="true"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import copy from 'copy-to-clipboard';

export default {
  name: "bookDetails",
  data() {
    return {
      book: {
        title: '',
        author: '',
        category: '',
        description: '',
        images: '',
        url: ''
      }
    }
  },
  methods: {
    selectById(id) {
      this.$http({
        method: "get",
        url: '/books/' + id
      }).then((res) => {
        if (res.data.code === 200) {
          this.book = res.data.data;
        }
      })
    },
    copyUrl(url) {
      copy('http://47.109.95.126:8080/' + url);
      this.$message({
        message: '复制成功',
        type: "success"
      });
    }
  },
  mounted() {
    this.selectById(this.$route.params.id);
  }
}
</script>

<style scoped>
@import "../assets/css/main.css";
</style>
