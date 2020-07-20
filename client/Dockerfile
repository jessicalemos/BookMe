FROM node:12

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json /app/package.json
RUN npm install
RUN npm install @vue/cli@4.4.6 -g

CMD ["npm", "run", "serve"]
