export default [
  {
    path: '/test',
    name: 'test',
    component: () => {
      return import('./test')
    }
  }
]
