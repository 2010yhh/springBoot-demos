export default [{
  path: '/login',
  redirect: {
    name: 'login'
  }
},
{
  path: '/login',
  name: 'login',
  component: () => {
    return import('./login')
  }
}
]
