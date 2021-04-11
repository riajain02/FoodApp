from clarifai.rest import ClarifaiApp

app = ClarifaiApp(api_key='60d1c478b15c41a29fe2b4e7180d9b06')
#model = app.public_models.general_model
model = app.models.get("food-items-v1.0")
response = model.predict_by_url(url='https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg')

i = 0
ingredients = ["","","","",""]
concepts = response['outputs'][0]['data']['concepts']
for concept in concepts:
	ingredients[i] = concept['name']
	i = i + 1
	if i == 5:
		break
for i in range(len(ingredients)):
	print(ingredients[i])

f = open("ingredients.txt", "w")
for i in range(len(ingredients)):
	f.write(ingredients[i]+"\n")
f.close()
