<template>
    <div>
        <Header></Header>
        <!-- <div>
            {{this.user_id}}
            {{ this.total }}
            {{ this.blogs }}
            
        </div> -->
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs" :key="blog.id">
                    <el-card>
                        <router-link :to="{name:'BlogDetail',params:{blogId:blog.id}}">
                            <h4>{{blog.title}}</h4>
                        </router-link>
                        <p>{{blog.description}}</p>
                    </el-card>
                </el-timeline-item>


            </el-timeline>
        </div>

        <el-pagination class="mpage"
                       background
                       layout="prev, pager, next"
                       :current-page="currentPage"
                       :page-size="pageSize"
                       :total="total"
                       @current-change=page
        >
        </el-pagination>
    </div>
</template>

<script>

    //导入公共的Header
    import Header from "../components/HeaderView";

    export default {
        name: "BlogsView",
        //将Header注册进去
        components: {Header},
        //返回的数据
        data() {
            return {
                blogs: {},
                currentPage: 1,  //当前页
                total: 0,    //总共多少页
                pageSize: 5,  //每一页的数据个数
                user_id: 1
            }
        },
        mounted() {
        },
        methods: {
            //请求指定页的方法
            // page(currentPage) {
            //     const _this = this;
            //     _this.$axios.get("/blogs?currentPage=" + currentPage,{
            //         headers: {
            //             "authorization": localStorage.getItem("token")
            //         }
            //     }).then(res => {
            //         console.log(res)
            //         //从获取到的数据中进行赋值
            //         _this.blogs = res.data.data.records
            //         _this.currentPage = res.data.data.current
            //         _this.total = res.data.data.total
            //         _this.pageSize = res.data.data.size
            //     })
            // }
            page(currentPage){
                const _this=this;
                _this.$axios.get("/blogs",{
                    params: {
                        pageNum: currentPage,
                        pageSize: 5,
                        user_id: 1
                    }
                }).then(res =>{
                    console.log(res.data);
                    _this.blogs=res.data.data
                    _this.currentPage=currentPage
                    _this.total=res.data.total
                    console.log(res.total);
                })
            }
        },
        created() {
            //const userInfo=JSON.parse(sessionStorage.getItem("user"));
            //this.user_id=userInfo.user_id

            this.page(1)
        }
    }
</script>

<style scoped>
    .mpage {
        margin: 0 auto;
        text-align: center;
    }
</style>