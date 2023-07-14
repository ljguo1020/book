<template>
  <div>

    <div style="margin-bottom: 50px"></div>

    <el-row :gutter="20">
      <el-col :span="6">
        <span>&nbsp;</span>
      </el-col>
      <el-col :span="12">
        <div class="title">添加书籍</div>
        <el-form :model="book" status-icon ref="ruleForm" label-width="100px">
          <el-form-item label="书名" prop="title">
            <el-input type="text" v-model="book.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input type="text" v-model="book.author" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="分类" prop="category">
            <el-input v-model="book.category"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="description">
            <el-input v-model="book.description" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="封面">
            <el-upload
              class="upload-demo"
              action="#"
              ref="addPicture"
              :auto-upload="false"
              :file-list="fileList"
              :multiple="false"
              list-type="picture-card">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="文件">
            <el-upload
              ref="addFiles"
              drag
              :auto-upload="false"
              action="#"
              :multiple="false"
              list-type="text"
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="addBook">提交</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="6">
        <span>&nbsp;</span>
      </el-col>
    </el-row>


  </div>
</template>

<script>
export default {
  name: "addBook",
  data() {
    return {
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
    addBook() {
      let formData = new FormData();
      formData.append("file", this.$refs.addFiles.uploadFiles[0].raw);

      formData.append("file", this.$refs.addPicture.uploadFiles[0].raw);
      console.log([this.$refs.addFiles.uploadFiles[0].raw, this.$refs.addPicture.uploadFiles[0].raw]);
      formData.append("book", new Blob([JSON.stringify(this.book)], {type: "application/json"}));
      console.log(new Blob([JSON.stringify(this.book)], {type: "application/json"}));
      this.$http({
        method: "post",
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        url: '/books',
        data: formData
      }).then((res) => {
        if (res.data.code === 200) {
          this.$message({
            message: "添加成功",
            type: "success"
          });
          this.book = {};
          this.fileList = [];
          this.$refs.addFiles.clearFiles();
          this.$refs.addPicture.clearFiles();
        }
      }).catch(() => {
        this.$message({
          message: '系统错误，请稍后重试',
          type: "error"
        })
      })
    }
  }
}
</script>

<style scoped>
@import "../assets/css/main.css";
</style>
