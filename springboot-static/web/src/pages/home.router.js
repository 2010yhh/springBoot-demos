export default [
  {
    path: '/home',
    name: 'home',
    component: () => {
      return import('./home')
    }
  }
]
