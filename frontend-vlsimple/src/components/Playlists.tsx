import React, { useEffect, useState } from 'react';
import { fetchPlaylists } from '../api/api.ts';

interface Playlist {
  id: number;
  name: string;
}

const PlaylistList: React.FC = () => {
  const [playlists, setPlaylists] = useState<Playlist[]>([]);

  useEffect(() => {
    const getPlaylists = async () => {
      const data = await fetchPlaylists();
      setPlaylists(data);
    };
    getPlaylists();
  }, []);

  return (
    <div>
      <h2>Playlist List</h2>
      <ul>
        {playlists.map((playlist) => (
          <li key={playlist.id}>{playlist.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default PlaylistList;
