import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

// Fetch all media
export const fetchMedia = async () => {
  const response = await axios.get(`${API_URL}/media`);
  return response.data;
};

// Fetch all playlists
export const fetchPlaylists = async () => {
  const response = await axios.get(`${API_URL}/playlists`);
  return response.data;
};
