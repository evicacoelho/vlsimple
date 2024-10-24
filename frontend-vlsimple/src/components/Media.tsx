import React, { useEffect, useState } from 'react';
import { fetchMedia } from '../api/api.ts';
import MediaUploadModal from './MediaUploadModal.tsx'

interface Media {
  id: number;
  title: string;
  artist: string;
  locale: string;
}

const MediaList: React.FC = () => {
  const [media, setMedia] = useState<Media[]>([]);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  useEffect(() => {
    const getMedia = async () => {
      const data = await fetchMedia();
      setMedia(data);
    };
    getMedia();
  }, []);

  const OpenModal = () => {
    setIsModalOpen(true);
  }

  const CloseModal = () => {
    setIsModalOpen(false);
  };


  const handleUploadSuccess = () => {
    setIsModalOpen(false);
    const getMedia = async () => {
      const data = await fetchMedia();
      setMedia(data);
    };
    getMedia()
  };

  return (
    <div>
      <h2>Media List</h2>
      <ul>
        {media.map((item) => (
          <li key={item.id}>
            {item.title} by {item.artist} - Path: {item.locale}
          </li>
        ))}
      </ul>
      <button onClick={() => OpenModal}>Upload Media</button>
      <MediaUploadModal
      
        isOpen={isModalOpen}
        onClose={() => CloseModal}
        onUploadSuccess={handleUploadSuccess}
      />
    </div>
  );
};

export default MediaList;
