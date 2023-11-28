<template>
    <div>
        <Header></Header>
        <div class="m_content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>

                 <el-form-item label="摘要" prop="description">
                    <el-input type="textarea" v-model="ruleForm.description"></el-input>
                </el-form-item>

                <el-form-item label="内容" prop="content">
                    <mavon-editor v-model="ruleForm.content"></mavon-editor>
                </el-form-item> 

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即发布</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import Header from "../components/HeaderView";

    export default {
        name: "BlogEdit",
        components: {Header},
        data() {
            return {
                ruleForm: {
                    id: "",
                    title: '',
                    description: '',
                    content: '',
                    user_id: ""
                },
                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {min: 5, max: 100, message: '长度在 5 到 100 个字符', trigger: 'blur'}
                    ],
                    description: [
                        {required: true, message: '请输入摘要', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入内容', trigger: 'blur'}
                    ],

                }
            };
        },
        mounted() {
            //console.log(localStorage.getItem("token"))
        },
        methods: {
            // submitForm(formName) {
            //     this.$refs[formName].validate((valid) => {
            //         if (valid) {
            //             const _this = this
            //             console.log(this.ruleForm)
            //             //发送编辑的请求
            //             _this.$axios.post("/blog/edit", this.ruleForm, {
            //                 //添加请求头部token
            //                 // headers: {
            //                 //     "authorization": localStorage.getItem("token")
            //                 // }
            //             }).then(res => {
            //                 console.log(res)
            //                 this.$alert('编辑操作成功！', '提示', {
            //                     confirmButtonText: '确定',
            //                     callback: action => {//eslint-disable-line no-unused-vars

            //                         _this.$router.push("/blogs")
            //                     }
            //                 });
            //             })

            //         } else {
            //             console.log('error submit!!');
            //             return false;
            //         }
            //     });
            // },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            submitForm(formName){
                this.$refs[formName].validate((valid)=>{
                    if(valid){
                        const _this=this
                        console.log(_this.ruleForm)
                        _this.$axios.post("/blog/edit",this.ruleForm).then(res=>{
                            console.log(res.data.status)
                            if(res.data.status=="ok")
                            {
                                this.$alert('编辑操作成功！', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {//eslint-disable-line no-unused-vars

                                    _this.$router.push("/blogs")
                                }
                                });
                            }
                            else{
                                this.$alert('编辑操作失败！', '提示', {
                                confirmButtonText: '确定'
                                // callback: action => {//eslint-disable-line no-unused-vars

                                //     _this.$router.push("/blogs")
                                // }
                                });
                            }
                            
                        })
                    }
                    else{
                        this.$alert('编辑失败','提示',{
                            confirmButtonText: '确定'
                        })
                        console.log('error submit!!');
                        return false;
                    }
                })
                    
            }
            
        },
        //页面渲染前的后置方法
        created() {
            //将博客信息回显，作用在进行编辑的时候可以在编辑页面出现用户的信息
            //用户发送“/blog/{id}/edit”类型的请求才会回显
            //用户发送“/blog/add”添加博客的请求时，获取不到值
            const blogId = this.$route.params.blogId
            console.log(blogId)
            const _this = this;
            const userInfo=JSON.parse(sessionStorage.getItem("user"));
            _this.ruleForm.user_id=userInfo.id
            // if (blogId){
            //     _this.$axios.get("/blog/" + blogId).then(res=>{
            //         const blog = res.data.data;
            //         _this.ruleForm.id = blog.id
            //         _this.ruleForm.title = blog.title
            //         _this.ruleForm.description = blog.description
            //         _this.ruleForm.content = blog.content

            //     })
            // }
            if(blogId){
                _this.$axios.get("/blog",{
                    params: {
                        blogId: blogId
                    }
                }).then(res=>{
                    const blog=res.data.blog
                    _this.ruleForm.id=blog.id
                    _this.ruleForm.title=blog.title
                    _this.ruleForm.description=blog.description
                    _this.ruleForm.content=blog.content
                    //const userInfo=JSON.parse(sessionStorage.getItem("user"));
                    //_this.ruleForm.user_id=userInfo.id
                    console.log(blog)
                })
            }
        }
    }
</script>

<style scoped>
    .m_content {
        margin: 0 auto;
    }
</style>