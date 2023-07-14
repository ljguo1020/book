<template>
  <div>

    <div class="header">
      <div class="logo">
        <img src="../assets/logo.jpg" alt="" width="30px" height="30px" style="vertical-align:middle"> 芒果不盲的数学小屋
      </div>
      <div class="text">
        让我们一起来学习数学和 LaTeX
      </div>
      <div class="add">
        <el-button type="primary" @click="addBooks">添加书籍</el-button>
      </div>

    </div>

    <div style="margin-bottom: 100px"></div>

    <el-row :gutter="20">
      <el-col :span="4">
        <span>&nbsp;</span>
      </el-col>
      <el-col :span="16">
        <div class="function">
          <div class="search">

            <el-form :inline="true" :model="pageBean" class="demo-form-inline">
              <el-form-item>
                <el-select v-model="pageBean.category" placeholder="分类">
                  <el-option label="全部" value=""></el-option>
                  <el-option v-for="category in categories" :label="category" :value="category" :key="category">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-input clearable autocomplete="on" placeholder="请输入要搜索的内容" v-model="pageBean.title"
                          class="input-with-select">
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button icon="el-icon-search" type="primary" @click="selectAll"></el-button>
              </el-form-item>
            </el-form>


          </div>
        </div>
        <el-row :gutter="20">
          <el-col :span="6" v-for="(item,index) in books" :key="index">
            <div id="card">
              <el-image
                style="width: 100%; height: 100%"
                :src="'http://47.109.95.126:8080/' + item.images"></el-image>
              <div class="cover">
                <div class="download">
                  <el-button type="success" icon="el-icon-download" @click="downloadFile(item.id)">下载</el-button>
                </div>
                <div class="details">
                  <el-button type="success" icon="el-icon-s-cooperation" @click="showDetails(item.id)">更多</el-button>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="4">
        <span>&nbsp;</span>
      </el-col>
    </el-row>

  </div>

</template>

<script>
export default {
  name: "Book",
  components: {},
  data() {
    return {
      books: [
        {
          id: 1,
          title: '高等数学',
          author: '同济大学',
          category: '数学类',
          description: '同济大学数学研究室',
          images: 'group1/M00/00/00/rBINY2Sf2v6AUp8JAAPVMukTzyk741.png',
          url: ''
        }
      ],
      categories: [],
      pageBean: {
        page: 1,
        size: 10000,
        title: '',
        category: ''
      },
      book: {
        title: '',
        author: '',
        category: '',
        description: '',
        images: '',
        url: ''
      },
      fileList: []
    }
  },
  methods: {
    selectAll() {
      this.$http({
        method: "post",
        url: '/books/page',
        data: this.pageBean
      }).then((res) => {
        if (res.data.code === 200) {
          this.books = res.data.data.list;
        }
      }).catch(() => {
        this.$message({
          message: '系统错误，请稍后重试',
          type: "error"
        });
      });
    },
    findCategories(){
        this.$http({
          method: "get",
          url: '/books/findCategories'
        }).then(res => {
          if (res.data.code === 200){
            this.categories = res.data.data;
          }
        })
    },
    downloadFile(id) {
      window.location.href = 'http://localhost:9999/books/downloadFile?id=' + id;

      const loading = this.$loading({
        lock: true,
        text: '拼命下载中，请耐心等待！',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });

      setTimeout(() => {
        loading.close();
      }, 5000);



    },
    addBooks() {
      this.$router.push("addBook");
    },
    showDetails(id) {
      this.$router.push({
        name: "bookDetails",
        params: {
          id: id
        }
      });
    }

  },
  mounted() {
    this.selectAll();
    this.findCategories();
  }
}
</script>

<style scoped>
@import "../assets/css/main.css";
</style>
