import moment from 'moment'
export const dateFormat = {
  methods: {
    onFormatTime(val, formatString = 'YYYY-MM-DD HH:mm:ss') {
      if (val && moment(val).isValid()) {
        return moment(val).format(formatString)
      }
      return '- -'
    }
  }
}

export default {
  dateFormat
}
