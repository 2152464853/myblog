<template>
    <div class="m_content">
        <h3>欢迎来到{{user.username}}的博客</h3>
        <div class="block">
            <el-avatar :size="50" :src="user.avatar"></el-avatar>
            <div>{{user.username}}</div>
        </div>

        <div class="maction">
            <span><el-link type="primary" href="/#/blogs">主页</el-link></span>

            <el-divider direction="vertical"></el-divider>
            <span><el-link type="success" href="/#/blog/add">发表博客</el-link></span>

            <span v-show="!haslogin">
                <el-divider direction="vertical"></el-divider>
                <span><el-link type="warning" href="/#/login">登录</el-link></span>
            </span>

            <span v-show="haslogin">
                <el-divider v-show="haslogin" direction="vertical"></el-divider>
                <span ><el-link type="danger" @click="logout">退出</el-link></span>
            </span>

        </div>
    </div>
</template>

<script>

    export default {
        name: "HeaderView",
        data() {
            return {
                //user: ""
                //user : window.sessionStorage.getItem("user")
                user: {
                    username: "请先登录",
                    avatar: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                    //avatar: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                }
                // haslogin: false
            }
        },
        //写入方法
        methods: {
            //退出登录
            logout() {
                const _this=this;
                window.sessionStorage.removeItem("user")
                _this.$router.push("/login")
                // const _this = this;
                // //发送退出登录请求
                // _this.$axios.get("/logout", {
                //     //由于只有在登录的时候才能进行退出，所以将token信息携带回去
                //     headers: {
                //         "authorization": localStorage.getItem("token")
                //     }
                // }).then(res => {//eslint-disable-line no-unused-vars

                //     //清空数据信息
                //     _this.$store.commit("REMOVE_INFO")
                //     //返回到登录界面
                //     _this.$router.push("/login")
                // })
            },
        },
        //执行一个初始化操作
        created() {
            //如果用户名存在，就回显出来
            // if (this.$store.getters.getUser.username) {
            //     this.user.username = this.$store.getters.getUser.username
            //     this.user.avatar = this.$store.getters.getUser.avatar
            //     this.haslogin = true
            // }
            //this.user=window.sessionStorage.getItem("user");
            const userInfo=JSON.parse(sessionStorage.getItem("user"));
            if(userInfo){
                this.user.username=userInfo.username;
                this.haslogin=true;
            }
            //this.user.username=userInfo.username;
            //this.user.avatar=userInfo.avatar;
        }
    }
</script>

<style scoped>
    .m_content {
        max-width: 960px;
        margin: 0 auto;
        text-align: center;
    }

    .maction {
        margin: 10px 0px;
    }
</style>