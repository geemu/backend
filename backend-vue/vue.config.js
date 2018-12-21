/**
 * @author 陈方明
 * @Date 2018-12-19
 * @Describtion vue-cli 3.x配置文件
 */
module.exports = {
  // 基本路径
  baseUrl: './',
  // 输出文件目录
  outputDir: 'dist',
  // eslint-loader 是否在保存的时候检查
  lintOnSave: true,
  // 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
  assetsDir: 'static',
  // 以多页模式构建应用程序。
  pages: undefined,
  // 是否使用包含运行时编译器的 Vue 构建版本
  runtimeCompiler: false,
  // 是否为 Babel 或 TypeScript 使用 thread-loader。该选项在系统的 CPU 有多于一个内核时自动启用，仅作用于生产构建，在适当的时候开启几个子进程去并发的执行压缩
  parallel: require('os').cpus().length > 1,
  // 生产环境是否生成 sourceMap 文件，一般情况不建议打开
  productionSourceMap: false,
  css: {
    // 启用 CSS modules
    modules: false,
    // 是否使用css分离插件
    extract: true,
    // 开启 CSS source maps，一般不建议开启
    sourceMap: false,
    // css预设器配置项
    loaderOptions: {
      sass: {}
    }
  },
  // webpack-dev-server 相关配置 https://webpack.js.org/configuration/dev-server/
  devServer: {
    // host: 'localhost',
    host: '0.0.0.0',
    port: 80, // 端口号
    https: false, // https:{type:Boolean}
    open: true, // 配置自动启动浏览器  http://XXX.XXX.X.XX:7071/rest/XXX/
    hotOnly: true, // 热更新
    // proxy: 'http://localhost:8000'           // 配置跨域处理,只有一个代理
    proxy: { // 配置自动启动浏览器
      '/XX/*': {
        target: 'http://XXX.XXX.X.XX:7071',
        changeOrigin: true,
        secure: false
      },
      '/x/*': {
        target: 'http://XXX.XXX.X.XX:2018',
        changeOrigin: true,
        ws: true, // websocket支持
        secure: false
      }
    }
  }
}
