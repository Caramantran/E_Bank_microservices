const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:9080',
      changeOrigin: false,
    }),
  );
  app.use(
    '/login',
    createProxyMiddleware({
      target: 'http://localhost:9080',
      changeOrigin: false,
    }),
  );
  app.use(
    '/login?logout',
    createProxyMiddleware({
      target: 'http://localhost:9080',
      changeOrigin: false,
    }),
  )
  app.use(
    '/logout',
    createProxyMiddleware({
      target: 'http://localhost:9080',
      changeOrigin: false,
    }),
  );
};