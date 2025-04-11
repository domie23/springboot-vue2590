const base = {
    get() {
        return {
            url : "http://localhost:8080/tushuguanzhanzuo/",
            name: "tushuguanzhanzuo",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/tushuguanzhanzuo/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "图书馆预约占座系统"
        } 
    }
}
export default base
