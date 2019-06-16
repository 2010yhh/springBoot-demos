export const TimeRange = {
  data() {
    return {
      options: [
        {
          label: '最近1小时',
          value: 'hour'
        },
        {
          label: '昨天',
          value: 'day'
        },
        {
          label: '过去一周',
          value: 'week'
        },
        {
          label: '过去一个月',
          value: 'month'
        }
      ],
      type: 'hour',
    }
  }
}

export default {
  TimeRange
}
