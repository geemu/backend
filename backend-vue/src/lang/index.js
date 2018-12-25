import Vue from 'vue'
import VueI18n from 'vue-i18n'
import JsCookies from 'js-cookie'
import ElementEnLocale from 'element-ui/lib/locale/lang/en'
import ElementZhLocale from 'element-ui/lib/locale/lang/zh-CN'
import EnLocale from './en'
import ZhLocale from './zh'

Vue.use(VueI18n)

const messages = {
  en: {
    ...EnLocale,
    ...ElementEnLocale
  },
  zh: {
    ...ZhLocale,
    ...ElementZhLocale
  }
}
const I18n = new VueI18n({
  locale: JsCookies.get('language') || 'zh',
  messages
})
export default I18n
