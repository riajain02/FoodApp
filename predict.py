from clarifai.rest import ClarifaiApp

app = ClarifaiApp(api_key='60d1c478b15c41a29fe2b4e7180d9b06')
model = app.public_models.general_model
response = model.predict_by_url(url='https://samples.clarifai.com/metro-north.jpg')

concepts = response['outputs'][0]['data']['concepts']
for concept in concepts:
    print(concept['name'], concept['value'])
