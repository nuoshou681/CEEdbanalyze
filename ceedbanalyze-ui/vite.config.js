import { fileURLToPath, URL } from 'node:url'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import postCssPxToRem from 'postcss-px2rem-exclude';
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 8080
  },
  // css: {
  //   postcss: {
  //     plugins: [
  //       postCssPxToRem({
  //         remUnit: 198,  // 设计稿宽度/10
  //         exclude: /(node_modules)/   //过滤三发ui样式，不进行转换
  //       })
  //     ]
  //   }
  // },
})
