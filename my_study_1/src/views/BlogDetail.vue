<template>
    <div>
        <Header></Header>
        <div class="mblog">
            <h2>{{blog.title}}</h2>
            <div>
                <el-link icon="el-icon-edit" v-if="ownBlog">
                    <!--携带博客ID跳转到编辑页面，对博客进行修改-->
                    <router-link :to="{name:'BlogEdit',params:{blogId: blog.id}}">
                        编辑
                    </router-link>
                </el-link>

                <el-divider direction="vertical"></el-divider>

                <el-link type="danger" @click="messageHandel" v-if="ownBlog">
                        删除
                </el-link>
            </div>


            <el-divider></el-divider>
            <div class="markdown-body" v-html="blog.content"></div>
        </div>
        <el-dialog
                :title="title"
                :visible="centerDialogVisible"
                width="30%"
                center>
        <span class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="deleteBlog">确 定</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script>
    // 导入所需依赖
    import Header from "../components/HeaderView";
    //为了让markdown转换的文本更好看，使用这个文件进行渲染
    import "github-markdown-css/github-markdown.css"
    //import messageModel from "../components/messageModel";

    export default {
        name: "BlogDetail",
        components: {Header},
        data() {
            return {
                centerDialogVisible: false,
                title:'您确定要删除此条博客吗？',
                blog: {
                    id: "",
                    title: "",
                    content: ""
                },
                ownBlog: false
            }
        },
        methods:{
            messageHandel(){
                this.centerDialogVisible = true
                console.log(this.centerDialogVisible)
                this.deleteBlog()
            },
            deleteBlog(){
                const _this = this
                const blogid = this.$route.params.blogId
                console.log(blogid)
                _this.$axios.get("/blog/delete",{
                    params:{
                        id: blogid
                    }
                }).then(res=>{
                    console.log(res)
                    const status = res.data.status
                    if (status=="ok"){
                        this.centerDialogVisible = false
                        this.$alert('删除成功！', '提示', {
                            confirmButtonText: '确定',
                        });
                        _this.$router.push("/blogs")

                    }else {
                        this.$alert('删除失败！', '提示', {
                            confirmButtonText: '确定',
                        });
                    }
                })
            }
        },
        created() {
            //从路径中获取到博客id
            const blogId = this.$route.params.blogId
            console.log(blogId)
            const _this = this
            //按照博客id找到指定的博客，
            // _this.$axios.get("/blog/" + blogId).then(res => {
            //     //获取到返回的博客信息，并且将该信息回显给页面
            //     const blog = res.data.data;

            //     _this.blog.id = blog.id
            //     _this.blog.title = blog.title
            //     _this.blog.description = blog.description

            //     /**
            //      * 将markdown的文本转为正常文本
            //      * */
            //         //拿到markdown渲染资源对象
            //     var markDownIT = require("markdown-it")
            //     var md = new markDownIT()   //获取到markdown-it的对象
            //     var result = md.render(blog.content)    //将markdown文本渲染成html文本
            //     _this.blog.content = result //将正常文本赋值给页面显示

            //     //只有在当前的博客的用户id和当前登录的用户的id相符合的时候，才会显示编辑按钮
            //     _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)

            // })
            _this.$axios.get("/blog",{
                params:{
                    blogId: blogId
                }
            }).then(res=>{
                _this.blog.id=res.data.blog.id
                _this.blog.content=res.data.blog.content
                _this.blog.title=res.data.blog.title

                    /**
                 * 将markdown的文本转为正常文本
                 * */
                    //拿到markdown渲染资源对象
                var markDownIT = require("markdown-it")
                var md = new markDownIT()   //获取到markdown-it的对象
                var result = md.render(_this.blog.content)    //将markdown文本渲染成html文本
                _this.blog.content = result //将正常文本赋值给页面显示

                //只有在当前的博客的用户id和当前登录的用户的id相符合的时候，才会显示编辑按钮
                const userInfo=JSON.parse(sessionStorage.getItem("user"));
                _this.ownBlog = (res.data.blog.user_id === userInfo.id)
            })
        }
    }

</script>

<style scoped>
    .mblog {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 100%;
        min-height: 760px;
        margin: 10px 15px;
    }
    :deep(.el-dialog__body){
        padding: 0 !important
    }
</style>